use clap::{App, ArgMatches, Subcommand};

mod gen_components;
mod gen_manifest;
mod gen_routes;

fn main() {
    let matches = App::new("App")
        .version("1.0")
        .about("CLI tool")
        .subcommand(SubCommand::with_name("manifest").about("Generates the manifest"))
        .subcommand(SubCommand::with_name("components").about("Generates the components"))
        .subcommand(SubCommand::with_name("routes").about("Generates the routes"))
        .get_matches();

    match matches.subcommand() {
        ("manifest", Some(_)) => gen_manifest(),
        ("components", Some(_)) => gen_components(),
        ("routes", Some(_)) => gen_routes(),
        _ => eprintln!("Invalid command. Use --help to see available commands."),
    }
}

fn gen_manifest() {
    gen_manifest::main();
}

fn gen_components() {
    gen_components::main();
}

fn gen_routes() {
    gen_routes::main();
}
