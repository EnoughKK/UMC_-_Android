package com.umc.badjang.HomePage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.badjang.HomeMorePage.MySchoolFragment
import com.umc.badjang.MainActivity
import com.umc.badjang.R
import com.umc.badjang.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeBinding // viewBinding

    val mySchoolSampleData = mutableListOf(mutableListOf("자기추천장학금", 215), mutableListOf("삼금문화장학재단 장학금", 215),
        mutableListOf("서울희망 대학 장학금", 215), mutableListOf("대산농촌재단 장학금", 215))

    // 우리대학 장학금 리스트 recyclerview adapter
    private val mySchoolDatas = mutableListOf<MainMySchoolData>()
    private lateinit var mainMySchoolAdapter: MainMySchoolAdapter

    // 인기글 리스트 recyclerview adapter
    private val popularDatas = mutableListOf<MainPopularData>()
    private lateinit var mainPopularAdapter: MainPopularAdapter

    // 전국소식 리스트 recyclerview adapter
    private val nationalNewsDatas = mutableListOf<MainNationalNewsData>()
    private lateinit var mainNationalNewsAdapter: MainNationalNewsAdapter

    // 프래그먼트 전환을 위해
    var activity: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(layoutInflater);

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar) //커스텀한 toolbar를 액션바로 사용

        // recyclerview 세팅
        initRecycler()

        // 우리학교 장학금 데이터 추가
        for(i: Int in 0..(mySchoolSampleData.size - 1)) {
            addMySchoolData(MainMySchoolData(i+1, mySchoolSampleData[i][0].toString(), 215))
        }

        // 인기글 데이터 추가
        for(i: Int in 0..3) {
            addPopularData(MainPopularData(i+1, "자기추천장학금 신청방법", 65,215))
        }

        // 전국 소식 데이터 추가
        for(i: Int in 0..3) {
            val img: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.national_news_img1)
            addNationalNewsData(MainNationalNewsData(img, "국가근로장학금"))
        }

        // 우리학교 장학금 더보기 버튼 클릭
        viewBinding.mainMySchoolMore.setOnClickListener(View.OnClickListener {
            activity?.changeFragment(MySchoolFragment())
        })
    }

    // recyclerview 세팅
    private fun initRecycler() {
        // 우리학교 장학금 recyclerview 세팅
        mainMySchoolAdapter = MainMySchoolAdapter(requireContext())
        viewBinding.mainMySchoolRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewBinding.mainMySchoolRecyclerview.adapter = mainMySchoolAdapter
        mainMySchoolAdapter.datas = mySchoolDatas

        // 인기글 recyclerview 세팅
        mainPopularAdapter = MainPopularAdapter(requireContext())
        viewBinding.mainPopularRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewBinding.mainPopularRecyclerview.adapter = mainPopularAdapter
        mainPopularAdapter.datas = popularDatas

        // 전국 소식 recyclerview 세팅
        mainNationalNewsAdapter = MainNationalNewsAdapter(requireContext())
        viewBinding.mainNationalNewsRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewBinding.mainNationalNewsRecyclerview.adapter = mainNationalNewsAdapter
        mainNationalNewsAdapter.datas = nationalNewsDatas
    }

    // 우리학교 장학금 데이터 추가
    private fun addMySchoolData(mainMySchoolPost: MainMySchoolData) {
        mySchoolDatas.apply {
            add(mainMySchoolPost)
        }
        mainMySchoolAdapter.notifyDataSetChanged()
    }

    // 인기글 데이터 추가
    private fun addPopularData(mainPopularPost: MainPopularData) {
        popularDatas.apply {
            add(mainPopularPost)
        }
        mainPopularAdapter.notifyDataSetChanged()
    }

    // 전국 소식 데이터 추가
    private fun addNationalNewsData(mainNationalNewsPost: MainNationalNewsData) {
        nationalNewsDatas.apply {
            add(mainNationalNewsPost)
        }
        mainNationalNewsAdapter.notifyDataSetChanged()
    }
}