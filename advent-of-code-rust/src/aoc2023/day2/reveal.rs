use regex::Regex;

const PATTERN: &'static str = r"(?<color1>\d+ \w+)(, (?<color2>\d+ \w+))?(, (?<color3>\d+ \w+))?";

#[derive(Debug)]
pub struct Reveal {
    pub reds: i32,
    pub blues: i32,
    pub greens: i32,
}

impl Reveal {
    pub fn is_reveal_possible(&self) -> bool {
        self.reds <= 12
            && self.greens <= 13
            && self.blues <= 14
    }
}

impl Into<Reveal> for &str {
    fn into(self) -> Reveal {
        let re = Regex::new(PATTERN).expect("Should be a valid regex pattern");
        let capt = re.captures(self).expect("Should match regex");

        let mut reveal = Reveal{
            reds: 0,
            blues: 0,
            greens: 0,
        };

        set_color(&mut reveal, capt.name("color1").unwrap().as_str());
        if capt.name("color2").is_some() {
            set_color(&mut reveal, capt.name("color2").unwrap().as_str());

            if capt.name("color3").is_some() {
                set_color(&mut reveal, capt.name("color3").unwrap().as_str());
            }
        }

        reveal
    }
}

fn set_color(reveal: &mut Reveal, desc: &str) {
    let (number_desc, color) = desc.split_once(" ").expect("Should have space");
    let number = number_desc.parse().expect("Should be a valid number");
    match color {
        "red" => reveal.reds = number,
        "green" => reveal.greens = number,
        "blue" => reveal.blues = number,
        _ => panic!("Invalid color: {}", color)
    }
}
