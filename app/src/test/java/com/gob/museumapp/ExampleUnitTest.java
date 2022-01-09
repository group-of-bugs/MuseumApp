package com.gob.museumapp;

import org.junit.Test;
import static org.junit.Assert.*;
import android.util.Log;
import com.gob.museumapp.db.DBHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        DBHelper helper = new DBHelper();
        helper.setSql("insert into user(phone, password, displayname) values('18515642763', 'WynnLu100', 'RicardoMLu')");
        int rs = helper.executeUpdate();

        System.out.println(rs);
    }
}