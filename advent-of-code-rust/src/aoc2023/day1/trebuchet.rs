use regex::{Captures, Regex};
use crate::prelude::*;

pub struct Trebuchet;

const PATTERN: &'static str = r"^[[:alpha:]]*?(?<first>[0-9])[[:alnum:]]*?(?<second>[0-9])?[[:alpha:]]*?$";

impl solution::Solution for Trebuchet {

    fn solve_part_one(&self, input_content: &String) -> String {
        let Ok(re) = Regex::new(PATTERN) else { panic!("Invalid pattern {PATTERN}") };

        input_content.lines()
            .map(|l| get_two_digit_number(l, &re))
            .sum::<i32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().map(&str::len).max().unwrap().to_string()
    }
}

fn get_two_digit_number(line: &str, re: &Regex) -> i32 {
    let Some(cap) = re.captures(line) else { panic!("Did not match regex: {line}") };
    let (first_digit, second_digit) = extract_digits(&cap);
    let two_digit_string = first_digit.to_string() + second_digit;
    two_digit_string.parse().unwrap()
}

fn extract_digits<'a>(cap: &'a Captures) -> (&'a str, &'a str) {
    let first_digit = &cap["first"];
    let second_digit = cap.name("second").map_or(first_digit, |m| m.as_str());
    (first_digit, second_digit)
}
