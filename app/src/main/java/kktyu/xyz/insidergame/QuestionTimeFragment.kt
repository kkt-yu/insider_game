package kktyu.xyz.insidergame


import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kktyu.xyz.insidergame.databinding.FragmentCountDownBinding

class QuestionTimeFragment : Fragment() {
    lateinit var binding: FragmentCountDownBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountDownBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timer = object : CountDownTimer(300000, 250) {
            override fun onTick(millisUntilFinished: Long) {
                val millisSec = millisUntilFinished / 1000
                val sec = "%02d".format(millisSec % 60)
                val min = millisSec / 60

                binding.remainingTime = "$min:$sec"
            }

            override fun onFinish() {
                beginDiscussionTimeFragment()
            }
        }
        timer.start()

        binding.answerButton.setOnClickListener {
            beginDiscussionTimeFragment()
        }
    }

    private fun beginDiscussionTimeFragment() {
        fragmentManager!!.beginTransaction().replace(
            R.id.container,
            DiscussionTimeFragment()
        ).commit()
    }

}
