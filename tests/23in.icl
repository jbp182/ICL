let f : (int)int = fun x : int-> x + 1 end in let g : (int)int = fun y : int -> f(y) + 2 end in let x : int = g(2) in x+x end end end
