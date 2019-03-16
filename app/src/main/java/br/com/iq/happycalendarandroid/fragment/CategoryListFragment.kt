package br.com.iq.happycalendarandroid.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.happycalendarandroid.HappyCalendarApplication

import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.adapter.CategoryAdapter
import br.com.iq.happycalendarandroid.domain.Category


class CategoryListFragment : BaseFragment() {

    private var categories: List<Category> = ArrayList()
    var rvCategory: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categories = HappyCalendarApplication.categories

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCategory = view?.findViewById(R.id.rvCategory)
        rvCategory?.layoutManager = LinearLayoutManager(activity)
        rvCategory?.itemAnimator = DefaultItemAnimator()
        rvCategory?.setHasFixedSize(true)
    }

    override fun onResume(){
        super.onResume()
        setupAdapter(categories)
    }

    private fun setupAdapter(list: List<Category>){
        rvCategory?.adapter = CategoryAdapter(categories) { onClickItem(it) }
    }

    private fun onClickItem(category: Category) {

    }

}
