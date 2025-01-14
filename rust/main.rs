use clap::{Command, ArgMatches, arg};

fn configure(command: Command) -> ArgMatches {
    return command.arg(arg!(-a --all <ALL> "all"))
        .get_matches();
}

fn main() {
    let arg_matches = configure(Command::new("DevTools"));

    if let Some(all) = arg_matches.get_one::<String>("all") {
        println!("option <all>: {}", all);
    }

}