use std::io;

fn fac(n: i32) -> (i32){
    if n == 1{
        1
    }else {
        n * fac (n-1)
    }
}

fn main() {
    println!("Ingrese el n:");
    let mut n = String::new();

    io::stdin().read_line(&mut n)
        .expect("Failed to read line");

    if let Ok(n) = n.trim().parse::<i32>(){
        println!("resultado {}",fac(n));
    }
}