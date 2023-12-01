use regex::{Captures, Regex};

use crate::prelude::*;

pub struct Trebuchet;

const DIGITS_PATTERN: &'static str = r"^[[:alpha:]]*?(?<first>[0-9])[[:alnum:]]*?(?<second>[0-9])?[[:alpha:]]*?$";

impl solution::Solution for Trebuchet {
    fn solve_part_one(&self, input_content: &String) -> String {
        let Ok(re) = Regex::new(DIGITS_PATTERN) else { panic!("Invalid pattern {}", DIGITS_PATTERN) };

        input_content.lines()
            .map(|l| get_two_digit_number(l, &re))
            .sum::<i32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        let forward_pattern = r"[0-9]|one|two|three|four|five|six|seven|eight|nine";
        let backward_pattern = r"[0-9]|enin|thgie|neves|xis|evif|ruof|eerht|owt|eno";

        let Ok(forward_re) = Regex::new(forward_pattern) else { panic!("Invalid pattern {}", forward_pattern) };
        let Ok(backward_re) = Regex::new(backward_pattern) else { panic!("Invalid pattern {}", backward_pattern) };

        input_content.lines()
            .map(|l| get_two_digit_number_with_bidirectional_search(l, &forward_re, &backward_re))
            .sum::<i32>()
            .to_string()
    }
}

fn get_two_digit_number(line: &str, re: &Regex) -> i32 {
    let Some(cap) = re.captures(line) else { panic!("Did not match regex: {}", line) };
    let (first_digit, second_digit) = extract_digits(&cap);
    let two_digit_string = first_digit.to_string() + second_digit;
    two_digit_string.parse().unwrap()
}

fn extract_digits<'a>(cap: &'a Captures) -> (&'a str, &'a str) {
    let first_digit = extract_digit(&cap["first"]);
    let second_digit = cap.name("second")
        .map_or(first_digit, |m| extract_digit(m.as_str()));
    (first_digit, second_digit)
}

fn get_two_digit_number_with_bidirectional_search(line: &str, forward_re: &Regex, backward_re: &Regex) -> i32 {
    let rev_line: String = line.chars().rev().collect();

    let first_match = forward_re.find(line).unwrap().as_str();
    let second_match_rev = backward_re.find(rev_line.as_str()).unwrap().as_str();

    let second_match: String = second_match_rev.chars().rev().collect();

    let first_digit = extract_digit(first_match);
    let second_digit = extract_digit(second_match.as_str());

    let two_digit_string = first_digit.to_string() + second_digit;
    two_digit_string.parse().unwrap()
}

fn extract_digit(matched: &str) -> &str {
    match matched {
        "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" => matched,
        "one" => "1",
        "two" => "2",
        "three" => "3",
        "four" => "4",
        "five" => "5",
        "six" => "6",
        "seven" => "7",
        "eight" => "8",
        "nine" => "9",
        _ => panic!("Could not parse {matched}")
    }
}
