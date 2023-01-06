package com.umc.badjang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.umc.badjang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding // viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(binding.fragmentLayout.id, HomeFragment()).commit()
        // navigationBottomView 등록: 하단바 fragment id(bottom_navigation) 등록
        transitionNavigationBottomView(binding.bottomNavigation, supportFragmentManager)
    }

    // NavigationBottomView 화면 전환하는 함수.
    private fun transitionNavigationBottomView(bottomView: BottomNavigationView, fragmentManager: FragmentManager){
        bottomView.setOnItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.home -> {
                    fragmentManager.beginTransaction().replace(binding.fragmentLayout.id, HomeFragment()).commit()
                }
                R.id.scholarship -> {
                    fragmentManager.beginTransaction().replace(binding.fragmentLayout.id, ScholarshipFragment()).commit()
                }
                R.id.post -> {
                    fragmentManager.beginTransaction().replace(binding.fragmentLayout.id, PostFragment()).commit()
                }
                R.id.search -> {
                    fragmentManager.beginTransaction().replace(binding.fragmentLayout.id, SearchFragment()).commit()
                }
                R.id.mypage -> {
                    fragmentManager.beginTransaction().replace(binding.fragmentLayout.id, MyPageFragment()).commit()
                }
                else -> Log.d("test", "error") == 0
            }
            Log.d("test", "final") == 0
        }
    }
}