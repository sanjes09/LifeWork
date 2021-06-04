use std::io;

fn fib(n: i32) -> (i32){
    if n>2 {
      fib(n-1) + fib(n-2)
    }else if n==2{
        1
    }else if n==1{
        1
    }else{
        0
    }
}

fn main() {
    println!("Ingrese el n:");
    let mut n = String::new();

    io::stdin().read_line(&mut n)
        .expect("Failed to read line");

    if let Ok(n) = n.trim().parse::<i32>(){
        println!("resultado {}",fib(n));
    }
}