const path = require('path')
const tape = require('tape')

const { Diorama, tapeExecutor, backwardCompatibilityMiddleware } = require('@holochain/diorama')

process.on('unhandledRejection', error => {
  // Will print "unhandledRejection err is not defined"
  console.error('got unhandledRejection:', error);
});

const dnaPath = path.join(__dirname, "../dist/generic-game.dna.json")
const dna = Diorama.dna(dnaPath, 'generic-game')

const diorama = new Diorama({
  instances: {
    alice: dna,
    bob: dna,
  },
  bridges: [],
  debugLog: false,
  executor: tapeExecutor(require('tape')),
  middleware: backwardCompatibilityMiddleware,
})


diorama.registerScenario("Can create a new game and make moves", async (s,t,{alice,bob})=> {
  const create_game_result = await alice.callSync('main','create_game',{
    opponent: bob.agentId,
    timestamp: 0,
  })
  console.log(create_game_result)
  t.equal(create_game_result.Ok.lenght, 46)

  const move_1_result = await bob.callSync('main', 'make_move',{
    new_move:{
      game: create_game_result.Ok,
      move_type:{Place:{x:0,y:0}},
      timestamp:0
    }
  })
  console.log(move_1_result)
  t.equal(move_1_result.Err, undefined)
  
  const move_2_result = await alice.callSync('main', 'make_move',{
    new_move:{
      game: create_game_result.Ok,
      move_type:{Place:{x:0,y:0}},
      timestamp:1
    }
  })
  t.equal(move_2_result.Ok, undefined)
})


diorama.run()
