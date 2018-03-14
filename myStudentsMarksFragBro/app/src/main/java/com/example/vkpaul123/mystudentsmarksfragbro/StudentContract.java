package com.example.vkpaul123.mystudentsmarksfragbro;

import android.provider.BaseColumns;

/**
 * Created by vkpaul123 on 7/3/18.
 */


public final class StudentContract {
    private StudentContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String tableName="studentmarks_table";
        public static final String col1="reg_no";
        public static final String col2="name";
        public static final String col3="parentphno";
        public static final String col4="marks1";
        public static final String col5="marks2";
        public static final String col6="marks3";
        public static final String col7="marks4";
        public static final String col8="total";
        public static final String col9="percent";
    }
}
