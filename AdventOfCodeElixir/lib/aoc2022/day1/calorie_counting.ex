defmodule CalorieCounting do
  @moduledoc false

  def solve_part_one(lines), do: lines |> do_solve(1)
  def solve_part_two(lines), do: lines |> do_solve(3)

  defp do_solve(calories_list, top_elves_to_find) do
    calories_list
    |> get_per_elf_calories
    |> Enum.sort(:desc)
    |> Enum.take(top_elves_to_find)
    |> Enum.sum()
  end

  defp get_per_elf_calories(calories_list) do
    chunk_fun = fn
      "", acc -> {:cont, acc, []}
      calories, acc -> {:cont, [calories | acc]}
    end

    after_fun = fn
      [] -> {:cont, []}
      acc -> {:cont, acc, []}
    end

    calories_list
    |> Enum.chunk_while([], chunk_fun, after_fun)
    |> Enum.flat_map(fn calories ->
      calories_sum = calories |> Enum.map(&String.to_integer/1) |> Enum.sum()
      [calories_sum]
    end)
  end
end
