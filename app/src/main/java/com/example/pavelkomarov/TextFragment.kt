package com.example.pavelkomarov

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pavelkomarov.databinding.FragmentTextBinding

class TextFragment : Fragment() {
    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val corrupted = corrupt(s.toString())
                binding.textView.text = corrupted
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.button.setOnClickListener {
            (activity as MainActivity).replaceFragment(ImageFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun corrupt(input: String): String {
        val transcriptionMap = mapOf(
            'q' to 'й', 'w' to 'ц', 'e' to 'у', 'r' to 'к', 't' to 'е', 'y' to 'н',
            'u' to 'г', 'i' to 'ш', 'o' to 'щ', 'p' to 'з', '[' to 'х', ']' to 'ъ',
            'a' to 'ф', 's' to 'ы', 'd' to 'в', 'f' to 'а', 'g' to 'п', 'h' to 'р',
            'j' to 'о', 'k' to 'л', 'l' to 'д', ';' to 'ж', '\\' to 'э', 'z' to 'я',
            'x' to 'ч', 'c' to 'с', 'v' to 'м', 'b' to 'и', 'n' to 'т', 'm' to 'ь',
            ',' to 'б', '.' to 'ю', '/' to '.', 'Q' to 'Й', 'W' to 'Ц', 'E' to 'У',
            'R' to 'К', 'T' to 'Е', 'Y' to 'Н', 'U' to 'Г', 'I' to 'Ш', 'O' to 'Щ',
            'P' to 'З', '{' to 'Х', '}' to 'Ъ', 'A' to 'Ф', 'S' to 'Ы', 'D' to 'В',
            'F' to 'А', 'G' to 'П', 'H' to 'Р', 'J' to 'О', 'K' to 'Л', 'L' to 'Д',
            ':' to 'Ж', '"' to 'Э', 'Z' to 'Я', 'X' to 'Ч', 'C' to 'С', 'V' to 'М',
            'B' to 'И', 'N' to 'Т', 'M' to 'Ь', '<' to 'Б', '>' to 'Ю', '?' to ','
        )

        return "Bamboozled!:\n" + input.map { transcriptionMap[it] ?: it }.joinToString("")
    }
}
