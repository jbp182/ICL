let x : int = 1 in let f : (int)int = fun y : int -> y + x end in let g : (int)int = fun x : int -> x + f( x ) end in g(2) end end end
