package com.example.vkpaul123.iamsqlitebro;

import android.provider.BaseColumns;

/**
 * Created by vkpaul123 on 21/2/18.
 */

public final class StudentContract {
    private StudentContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String tableName="student_table";
        public static final String col1="reg_no";
        public static final String col2="name";
        public static final String col3="class";
    }
}
