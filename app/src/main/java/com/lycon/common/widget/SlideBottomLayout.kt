package com.lycon.common.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.Scroller
import androidx.recyclerview.widget.RecyclerView
import com.lycon.common.R

class SlideBottomLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    /**
     * 手势按下位置记录
     */
    private var downY: Float = 0.toFloat()
    /**
     * 手势移动位置记录
     */
    private var moveY: Float = 0.toFloat()
    /**
     * 手势移动距离
     */
    private var movedDis: Int = 0
    /**
     * 移动的最大值
     */
    private var movedMaxDis: Int = 0
    /**
     * SlideBottom 的子视图
     */
    private var childView: View? = null
    /**
     * SlideBottom状态
     * isShow的两种状态 伸张与收缩
     */
    private var isShow: Boolean? = false
    /**
     * 状态切换阈值
     */
    private var hideWeight = 0.3f
    /**
     * 拦截器参数相关
     * 记录Action.Down按下位置
     * @param hideWeight
     */
    private var CurrentY: Int = 0

    /**
     * 视图滚动辅助
     */
    private var mScroller: Scroller? = Scroller(context)

    /**
     *
     * 标记：childView到达parent或者其他的顶部
     */
    private var arriveTop = false

    /**
     * 设置：childView的初始可见高度
     */
    private var visibilityHeight: Float = 0.toFloat()
    /**
     * 绑定的Rc
     */
    private var recyclerview: RecyclerView? = null

//    private var shortSlideListener: ShortSlideListener? = null

    init {
        initAttrs(context, attrs)
    }

    /**
     * 初始化属性配置
     * @param context the [Context]
     * @param attrs   the configs in layout attrs.
     */
    @SuppressLint("CustomViewStyleable")
    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SildeBottomStyles)
        visibilityHeight = ta.getDimension(com.lycon.common.R.styleable.SildeBottomStyles_visible_height, 0f)
        ta.recycle()
    }

    /**
     * 使用前判断/单一子视图
     * 该方法在OnMeasure(int,int)调用
     */
    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount == 0 || getChildAt(0) == null) {
            throw RuntimeException("SlideBottom里面没有子布局")
        }
        if (childCount > 1) {
            throw RuntimeException("SlideBottom里只可以放置一个子布局")
        }
        childView = getChildAt(0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        movedMaxDis = (childView!!.measuredHeight - visibilityHeight).toInt()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        childView!!.layout(
            0,
            movedMaxDis,
            childView!!.measuredWidth,
            childView!!.measuredHeight + movedMaxDis
        )
    }

//    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        val interceptY = ev.y
//        when (ev.action) {
//            MotionEvent.ACTION_DOWN -> RecordY(interceptY)
//            MotionEvent.ACTION_MOVE -> {
//                return if (interceptJudge(interceptY)) {
//                    onTouchEvent(ev)
//                } else false
//            }
//            MotionEvent.ACTION_UP -> {
//                return if (interceptJudge(interceptY)) {
//                    onTouchEvent(ev)
//                } else false
//            }
//        }
//        return super.onInterceptTouchEvent(ev)
//    }


//    /**
//     * 记录下拦截器传来的Y值
//     * @param interceptY
//     */
//    private fun RecordY(interceptY: Float) {
//        CurrentY = interceptY.toInt()
//    }
//
//    /**
//     * 拦截判断
//     * @param interceptY
//     * @return
//     */
//    private fun interceptJudge(interceptY: Float): Boolean {
//        val judgeY = CurrentY - interceptY
//        if (judgeY > 0) {
//            //向上滑动
//            if (!arriveTop()) {
//                return true
//            }
//        }
//        if (judgeY < 0) {
//            //向下滑动
//            if (arriveTop() && isTop(recyclerview)) {
//                return true
//            }
//        }
//        return false
//    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val dy = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> if (touchActionDown(dy)) {
                return true
            }
            MotionEvent.ACTION_MOVE -> if (touchActionMove(dy)) {
                return true
            }
            MotionEvent.ACTION_UP -> if (touchActionUp(dy)) {
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * scroll的更新方法
     * computeScrollOffset 返回true表示动画未完成
     */
    override fun computeScroll() {
        super.computeScroll()
        if (mScroller == null)
            mScroller = Scroller(context)
        if (mScroller!!.computeScrollOffset()) {
            scrollTo(0, mScroller!!.currY)
            postInvalidate()
        }
    }


    fun touchActionUp(eventY: Float): Boolean {
        //移动的位置是否大于阈值
        if (movedDis > movedMaxDis * hideWeight) {
            switchVisible()
        } else {
            //提供一个接口用于处理没有达到阈值的手势
//            if (shortSlideListener != null) {
//                shortSlideListener!!.onShortSlide(eventY)
//            } else {
            hide()
//            }
        }
        return true
    }

    fun touchActionMove(eventY: Float): Boolean {
        moveY = eventY
        //dy是移动距离的和 如果它的值>0表示向上滚动  <0表示向下滚动
        val dy = downY - moveY
        if (dy > 0) {               //向上
            movedDis += dy.toInt()
            if (movedDis > movedMaxDis) {
                movedDis = movedMaxDis
            }

            if (movedDis < movedMaxDis) {
                scrollBy(0, dy.toInt())
                downY = moveY
                return true
            }
        } else {                //向下
            movedDis += dy.toInt()
            if (movedDis < 0) {
                movedDis = 0
            }
            if (movedDis > 0) {
                scrollBy(0, dy.toInt())
            }
            downY = moveY
            return true
        }
        return false
    }

    fun touchActionDown(eventY: Float): Boolean {
        //记录手指按下的位置
        downY = eventY.toInt().toFloat()
        return !(!arriveTop && downY < movedMaxDis)
    }

    /**
     * slidBottom的显示方法
     */
    fun show() {
        scroll2TopImmediate()
    }

    /**
     * slidBottom的隐藏方法
     */
    fun hide() {
        scroll2BottomImmediate()
    }

    /**
     * arriveTop返回值
     * 判断child是否到达顶部
     */
    fun switchVisible() {
        if (arriveTop()) {
            hide()
        } else {
            show()
        }
//        return arriveTop()
    }

    fun arriveTop(): Boolean {
        return this.arriveTop
    }

    fun scroll2TopImmediate() {
        mScroller!!.startScroll(0, scrollY, 0, movedMaxDis - scrollY)
        invalidate()
        movedDis = movedMaxDis
        arriveTop = true
        isShow = true
    }

    fun scroll2BottomImmediate() {
        mScroller!!.startScroll(0, scrollY, 0, -scrollY)
        postInvalidate()
        movedDis = 0
        arriveTop = false
        isShow = false
    }

    /**
     * 绑定RecyclerView（可选）
     * 如果子布局有RecyclerView必须绑定否则Recyclerview的滑动不会被拦截
     * @param recyclerView
     */
    fun bindRecyclerView(recyclerView: RecyclerView) {
        this.recyclerview = recyclerView
    }

//    fun setShortSlideListener(listener: ShortSlideListener) {
//        this.shortSlideListener = listener
//    }

    /**
     * 隐藏比重阈值
     * @param hideWeight
     */
    fun setHideWeight(hideWeight: Float) {
        if (hideWeight <= 0 || hideWeight > 1) {
            throw IllegalArgumentException("隐藏的阈值应该在(0f,1f]之间")
        }
        this.hideWeight = hideWeight
    }

    /**
     * 设置显示高度
     * @param visibilityHeight
     */
    fun setVisibilityHeight(visibilityHeight: Float) {
        this.visibilityHeight = visibilityHeight
    }

//    companion object {
//
//
//        /**
//         * 绑定Recyclerview如果你的子布局中含有Recyclerview的话
//         * 该方法用于判断是否到达Recyclerview的顶部
//         * @param recyclerView
//         * @return
//         */
//        fun isTop(recyclerView: RecyclerView?): Boolean {
//            return if (recyclerView == null) {
//                false
//            } else !recyclerView.canScrollVertically(-1)
//        }
//    }


}
