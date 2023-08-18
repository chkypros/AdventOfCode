defmodule AdventOfCodeUtils do
  @moduledoc false

  def get_input_file_lines(file_path, label \\ "") do
    path_segments = file_path |> Path.split()
    path_segments_num = path_segments |> Enum.count()
    day = path_segments |> Enum.at(path_segments_num - 2)
    event = path_segments |> Enum.at(path_segments_num - 3)

    [event, day, "aoc-" <> day <> label <> "-input.txt"]
    |> Path.join()
    |> Path.expand("../inputs")
    |> File.stream!()
    |> Stream.map(&String.trim_trailing/1)
    |> Enum.to_list()
  end
end
