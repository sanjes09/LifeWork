use std::io;

fn foobar(n:i32){
    let mut s=String::new();
    for number in 1..n+1{
        s.clear();
        if number%3!=0 && number%5!=0{
            println!("{}",number);
        }else{
            if number%3==0{
                s.push_str("foo");
            }
            if number%5==0{
                s.push_str("bar");
            }
            println!("{}",s);
        }
    }
}

fn main() {
    println!("Ingrese el n:");
    let mut n = String::new();

    io::stdin().read_line(&mut n)
        .expect("Failed to read line");

    if let Ok(n) = n.trim().parse::<i32>(){
        foobar(n);
    }
}