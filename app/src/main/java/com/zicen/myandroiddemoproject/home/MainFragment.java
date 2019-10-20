package com.zicen.myandroiddemoproject.home;

import com.yjnull.latte_core.fragment.BaseMainFragment;
import com.yjnull.latte_core.fragment.bottom.BaseBottomDelegate;
import com.yjnull.latte_core.fragment.bottom.BottomTabBean;
import com.yjnull.latte_core.fragment.bottom.ItemBuilder;
import com.zicen.myandroiddemoproject.R;

import java.util.LinkedHashMap;


/**
 * Created by zicen
 */
public class MainFragment extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BaseMainFragment> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BaseMainFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean(R.mipmap.ic_discover_white_24dp, "发现"), new DiscoverFragment());
        items.put(new BottomTabBean(R.mipmap.ic_message_white_24dp, "消息"), new IndexFragment());
        items.put(new BottomTabBean(R.mipmap.ic_account_circle_white_24dp, "账户"), new IndexFragment());
        return items;
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

}
