export const consts = {
    CHAT : 'USE_CHAT',
    PROFILE : 'USE_PROFILE'
}

export default {
    loadChannels:(channels=[],dispatch)=>{
        dispatch({ type:'CHANNELS_UPDATE' , channels })
    },
    loadMyChannels:(channels=[],dispatch)=>{
        dispatch({ type:'MY_CHANNELS_UPDATE' , channels })
    },
    loadConversations:(channels=[],dispatch)=>{
        dispatch({ type:'CONVERSATIONS_UPDATE' , channels })
    },
    loadUsers:(users=[],dispatch)=>{
        dispatch({ type:'USERS_UPDATE' , users })
    },
    updateProfile:(profile={},dispatch)=>{
        dispatch({ type:'PROFILE_UPDATE' , profile })
    },
    loadCurrentChannel:(channel={},dispatch)=>{
        dispatch({ type:'UPDATE_CHANNEL' , channel })
    },
    loadCurrentChat:(chat=[],dispatch)=>{//array of messages
        dispatch({ type:'UPDATE_CHAT' , chat })
    },
    displayChannelsModal:(dispatch)=>{
        dispatch({ type:'DISPLAY_CHANNELS' })
    },
    displayConversationsModal:(dispatch)=>{
        dispatch({ type:'DISPLAY_CONVERSATIONS' })
    },
    closeModal:(dispatch)=>{
        dispatch({ type:'CLOSE_MODAL' })
    },
    selectApp : ( app , dispatch ) => {
        console.log(consts)
        if ( Object.values(consts).indexOf(app) > -1 ) {
            dispatch( { type: app } )
        } else {
            throw new Error( `App ${ app } not allowed` )
        }
    },
}