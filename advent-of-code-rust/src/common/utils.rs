use std::fs::read_to_string;
use regex::Regex;

pub fn function() -> String {
    "11806".to_string()
}

pub fn read_lines(filename: &str) -> String {
    read_to_string(filename)
        .expect("Expected to read the file")
}

pub fn get_input(filepath: &str) -> String {
    println!("{filepath}");
    let regex = Regex::new(r"src/(aoc\d{4})/(day\d{1,2})/").unwrap();
    let captures = regex.captures(filepath).unwrap();
    let aoc = captures.get(0).unwrap().as_str();
    let day = captures.get(1).unwrap().as_str();
    let input_file = format!("../inputs/{aoc}/{day}/aoc-{day}-input.txt");
    // let input_file = "../inputs/aoc2022/day1/aoc-day1-input.txt";
    let input_content = read_lines(input_file.as_str());
    input_content
}
