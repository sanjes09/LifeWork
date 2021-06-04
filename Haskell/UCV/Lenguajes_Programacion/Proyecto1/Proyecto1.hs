data Zona = ZonaC Int deriving(Eq,Show)
data Tunel = TunelC Zona Zona Int deriving(Eq,Show)
data Mapa = MapaC [Zona] [Tunel] deriving(Eq,Show)
data Marca = MarcaC Zona Bool deriving(Eq,Show)

--EN PELIGRO

inter :: [Zona] -> [Zona] -> [Zona]
inter x y = [t | t<-x, l<-y, t==l]

estaSpelunkyEnPeligro :: Mapa -> Mapa -> Bool
estaSpelunkyEnPeligro (MapaC z1 l1) (MapaC z2 l2) = length(inter z1 z2)>0

--ELIMINAR ZONAS

eliminarZonas :: [Zona] -> [Zona] -> [Zona]
eliminarZonas z3 z4 = [y | y<-z4, not (elem y z3)]

borrar :: Tunel -> [Zona] -> Bool
borrar (TunelC z1 z2 i1) ys | elem z1 ys = False | elem z2 ys = False | otherwise = True

eliminarTuneles :: [Zona] -> [Tunel] -> [Tunel]
eliminarTuneles z3 t4 = [t | t<-t4, borrar t z3]

eliminarMarcas :: Mapa -> Mapa -> (Mapa, Mapa)
eliminarMarcas (MapaC z1 l1) (MapaC z2 l2) = (MapaC (eliminarZonas (inter z1 z2) z1) (eliminarTuneles (inter z1 z2) l1), MapaC (eliminarZonas (inter z1 z2) z2) (eliminarTuneles (inter z1 z2) l2))

--PRENDE EL GPS

voltear :: [Tunel] -> [Tunel]
voltear [] = []
voltear ((TunelC z1 z2 i1):xs) = [(TunelC z2 z1 i1)]++voltear xs

crearLista :: [Zona] -> [Zona] -> [Marca]
crearLista z1 z2 = [(MarcaC x False) | x<-z1]++[(MarcaC y False) | y<-z2, not (elem y z1)]

valido2 :: Zona -> Marca -> Bool
valido2 z2 (MarcaC z b) = if(z2==z&&b==False) then True else False

valido :: Zona -> [Marca] -> [Marca]
valido z2 marca = [x | x<-marca, valido2 z2 x]

puede :: Zona -> Tunel -> [Marca]-> Bool
puede estoy (TunelC z1 z2 i1) marca = if(z1==estoy&&valido z2 marca/=[]) then True else False

hacia :: Zona -> [Tunel] -> [Marca] -> [Tunel]
hacia estoy t marca = [x | x<-t, puede estoy x marca]

funcion :: [Tunel] -> Zona -> [Tunel] -> [Marca] -> [Tunel]
funcion [] _ _ _ = []
funcion ((TunelC z1 z2 i1):xs) p2 tuneles marca = [(TunelC z1 z2 i1) ]++funcion (hacia z2 tuneles marca) p2 tuneles marca++funcion xs p2 tuneles marca

hasta :: Tunel -> Zona -> Bool
hasta (TunelC z1 z2 i1) x = if (z2/=x) then True else False

sacar :: Zona -> [Tunel] -> [[Tunel]]
sacar _ [] = []
sacar z2 lista = [(takeWhile (`hasta` z2) lista) ++ [(dropWhile (`hasta` z2) lista) !! 0]] ++ (sacar z2 (drop 1 (dropWhile (`hasta` z2) lista)))

hasta2 :: Tunel -> Tunel -> Bool
hasta2 (TunelC z1 z2 i1) (TunelC z3 z4 i2) = if (z1/=z3) then True else False

completar2 :: Zona -> Zona -> [Tunel] -> [Tunel] -> [Tunel]
completar2 _ _ _ [] = []
completar2 _ _ [] _ = []
completar2 z1 z2 t1 t2 = (takeWhile (`hasta2` (head (t2))) t1) ++ t2

completar :: Zona -> Zona -> [[Tunel]] -> [[Tunel]]
completar _ _ [] = []
completar _ _ (x:[]) = []
completar z1 z2 (x:lista) = [x]++completar z1 z2 (completar2 z1 z2 x (head (lista)):tail (lista))

fun :: [Tunel] -> Int
fun [] = 0
fun ((TunelC z1 z2 i1):xs) = i1 + fun xs

optimo :: [[Tunel]] -> [[Tunel]]
optimo [] = []
optimo (x:xs) = optimo ([y | y <- xs, (fun y) < (fun x)]) ++ [x] ++ optimo([l | l <- xs, (fun l) > (fun x)])

prendeElGPS :: Mapa -> Mapa -> [Tunel]
prendeElGPS (MapaC (z1:z1s) l1) (MapaC (z2:z2s) l2) = head (optimo (completar z1 z2 (sacar z2 (funcion (hacia z1 (l1++ l2) (crearLista (z1:z1s) (z2:z2s))) z2 (l1++ l2) (crearLista (z1:z1s) (z2:z2s))))))