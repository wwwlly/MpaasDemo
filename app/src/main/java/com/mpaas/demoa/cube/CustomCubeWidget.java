package com.mpaas.demoa.cube;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.antfin.cube.antcrystal.widget.CCardWidget;
import com.mpaas.mas.adapter.api.MPLogger;

import java.util.Map;

public class CustomCubeWidget extends CCardWidget {
    private static final String TAG = "CustomCubeWidget";

    public CustomCubeWidget(Context context) {
        super(context);
    }

    /**
     * UI创建接口，表示当前组件要上屏的视图UI
     *
     * @param params 组件在卡片上声明的数据，包括样式、事件和属性，对应的key为DATA_KEY_STYLES、DATA_KEY_EVENTS、DATA_KEY_ATTRS；
     * @param width  组件的宽度
     * @param height 组件的高度
     * @return 返回嵌入的View
     */
    @Override
    public View onCreateView(Map<String, Object> params, int width, int height) {
        MPLogger.debug(TAG, "onCreateView:" + width + "," + height);
        for (String key : params.keySet()) {
            MPLogger.debug(TAG, key + ":" + params.get(key));
        }
        TextView textView = new TextView(getContext());
        textView.setText("test");
        return textView;
    }

    /**
     * UI复用接口，如果组件支持复用（参见 canReuse 方法），当组件被复用重新上屏时调用。
     *
     * @param params 组件在卡片上声明的数据
     * @param width  组件的宽度
     * @param height 组件的高度
     */
    @Override
    public void onReuse(Map<String, Object> params, int width, int height) {
        MPLogger.debug(TAG, "onReuse");
    }

    /**
     * 组件数据更新接口
     *
     * @param params 组件在卡片上声明的数据
     */
    @Override
    public void onUpdateData(Map<String, Object> params) {
        MPLogger.debug(TAG, "onUpdateData");
    }

    /**
     * 组件复用清理接口。如果组件支持复用（canReuse 返回 true），则组件在进入复用池后会调用 onRecycleAndCached方法，表示当前组件已经离屏放入缓存内，需要清理资源。
     */
    @Override
    public void onRecycleAndCached() {
        MPLogger.debug(TAG, "onRecycleAndCached");
    }

    /**
     * 组件是否支持复用；为了提高效率，扩展组件可以支持复用。例如当某个自定义的标签组件由于数据更新被移除当前视图，此时该组件如果支持复用，那么会放入复用池内，下次该组件显示时，会直接从复用池内获取；如果组件不支持复用，则直接调用销毁接口(onDestroy)；
     *
     * @return true：复用，false：不复用
     */
    @Override
    public boolean canReuse() {
        MPLogger.debug(TAG, "canReuse");
        return false;
    }

    /**
     * 组件销毁回调接口，释放资源
     */
    @Override
    public void onDestroy() {
        MPLogger.debug(TAG, "onDestroy");
    }
}
