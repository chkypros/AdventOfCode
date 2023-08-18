defmodule AdventOfCodeUtils do
  use ExUnit.Case

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

  def run_test(config, mode), do:
    do_test(config[:file_path], config[:module_under_test], config[:expected_answer], mode)

  defp do_test(file_path, module, expected_answer, :part_one) do
    answer = file_path |> get_input_file_lines() |> module.solve_part_one()
    assert answer == expected_answer[:part_one]
  end

  defp do_test(file_path, module, expected_answer, :part_two) do
    answer = file_path |> get_input_file_lines() |> module.solve_part_two()
    assert answer == expected_answer[:part_two]
  end

  defp do_test(file_path, module, expected_answer, :part_one_sample) do
    answer = file_path |> get_input_file_lines("-sample") |> module.solve_part_one()
    assert answer == expected_answer[:part_one_sample]
  end

  defp do_test(file_path, module, expected_answer, :part_two_sample) do
    answer = file_path |> get_input_file_lines("-sample") |> module.solve_part_two()
    assert answer == expected_answer[:part_two_sample]
  end
end
