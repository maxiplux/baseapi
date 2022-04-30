package io.api.base.utilities;

import io.api.base.domain.EmailTypeEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UtilityTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void buildCron_DIARIO() {
        LocalDate cronDate=LocalDate.of(2022,4,24);
        LocalTime cronTime=LocalTime.of(9,30,0);
        String expectValue="0 30 9 ? * * *";
        assertEquals(expectValue, Utility.buildCron(cronDate,cronTime, EmailTypeEnum.DIARIO.toString()), "failure - strings are not equal");
    }

    @Test
    void buildCron_UNICA_VEZ() {
        LocalDate cronDate=LocalDate.of(2022,4,24);
        LocalTime cronTime=LocalTime.of(9,30,0);
        String expectValue="0 30 9 24 * ? *";
        assertEquals(expectValue, Utility.buildCron(cronDate,cronTime, EmailTypeEnum.MENSUAL.toString()), "failure - strings are not equal");
    }

    @Test
    void buildCron_oneTimeByDayAndMonthAndYearAndSpecificHour_Locals() {
        LocalDate cronDate=LocalDate.of(2022,4,24);
        LocalTime cronTime=LocalTime.of(9,30,0);
        String expectValue="0 30 9 24 4 ? 2022";
        assertEquals(expectValue, Utility.buildCron(cronDate,cronTime, EmailTypeEnum.UNICA_VEZ.toString()), "failure - strings are not equal");
    }


    @Test
    void simpleCron() throws ParseException {

        //CronScheduleBuilder.monthlyOnDayAndHourAndMinute(24,18,24);
        //CronScheduleBuilder.dailyAtHourAndMinute(18,24);
        assertNotNull(CronScheduleBuilder.cronSchedule(new CronExpression("0 31 22 24 4 ? 2022")));

    }




}
