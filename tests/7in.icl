let x : ref int = new 10 , s:ref int = new0 in while !x>0 do s := !s + !x ; x := !x – 1 end; !s end
