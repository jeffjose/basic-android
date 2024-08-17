use clap::{Command, SubCommand};
mod gen_manifest;
mod gen_components;
mod gen_routes;

fn main() {
    let matches = Command::new("MyApp")
        .version("1.0")
        .author("Author Name")
        .about("Generates routes, components, and manifests")
        .subcommand(Command::new("manifest").about("Generates a manifest"))
        .subcommand(Command::new("components").about("Generates components"))
        .subcommand(Command::new("routes").about("Generates routes"))
        .get_matches();

    match matches.subcommand() {
        Some(("manifest", _sub_m)) => gen_manifest::main(),
        Some(("components", _sub_m)) => gen_components::main(),
        Some(("routes", _sub_m)) => gen_routes::main(),
        _ => eprintln!("Invalid command. Use `--help` to see available commands."),
    }
}
