let f : (int,int)int = fun n:int, b:int -> let x:ref int = new n, s: ref int = new b in while !x>0 do s:= !s + !x ; x := !x - 1 end; !s end end in f(10,0)+f(100,20) end
