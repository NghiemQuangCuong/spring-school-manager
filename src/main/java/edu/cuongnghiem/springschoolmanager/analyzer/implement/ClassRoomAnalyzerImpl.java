package edu.cuongnghiem.springschoolmanager.analyzer.implement;

import edu.cuongnghiem.springschoolmanager.analyzer.ClassRoomAnalyzer;
import edu.cuongnghiem.springschoolmanager.analyzer.charts.BarChart;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.StudentService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuongnghiem on 24/10/2021
 **/
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ClassRoomAnalyzerImpl implements ClassRoomAnalyzer {

    private final ClassRoomService classRoomService;
    private final StudentService studentService;

    // add class room id to this string from the method, to save update history with that class room
    private final String STUDENTS = "STUDENT_CACHE_KEY_";

    private final Map<String, Pair<BarChart, LocalDateTime>> cache = new HashMap<>();

    public ClassRoomAnalyzerImpl(ClassRoomService classRoomService, StudentService studentService) {
        this.classRoomService = classRoomService;
        this.studentService = studentService;
    }

    /**
     * @param secondClassRoomId set null if you want to compare to all
     *
     * **/
    @Override
    public BarChart students(Long firstClassRoomId, Long secondClassRoomId, StringBuilder status) {
        ClassRoom firstCR = classRoomService.getClassRoomById(firstClassRoomId);
        ClassRoom secondCR = null;
        if (secondClassRoomId != null)
            secondCR = classRoomService.getClassRoomById(secondClassRoomId);
        String key = STUDENTS + firstClassRoomId.toString() + "_" + ((secondClassRoomId != null) ? secondClassRoomId.toString() : "all");
        Pair<BarChart, LocalDateTime> savedCache = cache.get(key);

        if (savedCache == null) {
            BarChart barChart = new BarChart();
            LocalDateTime time = LocalDateTime.now();
            analyzeStudent(key,
                    firstClassRoomId, firstCR.getName(),
                    secondClassRoomId, (secondClassRoomId != null) ? secondCR.getName() : null,
                    barChart, time, cache);
            status.append("Last update: ").append(time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            return barChart;
        } else {
            BarChart barChart =
                    savedCache.getFirst();
            LocalDateTime time = savedCache.getSecond();
            if (time.plusMinutes(1L).isBefore(LocalDateTime.now())) {
                // expired
                cache.remove(key);
                analyzeStudent(key,
                        firstClassRoomId, firstCR.getName(),
                        secondClassRoomId, (secondClassRoomId != null) ? secondCR.getName() : null,
                        barChart, time, cache);
                status.append("Last update: ").append(time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                return barChart;
            } else {
                status.append("Last update: ").append(time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                return barChart;
            }
        }
    }

    private void analyzeStudent(String key, Long firstClassRoomId, String firstClassRoomName, Long secondClassRoomId, String secondClassRoomName, BarChart barChart, LocalDateTime time, Map<String, Pair<BarChart, LocalDateTime>> cache) {
        long firstCRStudent = classRoomService.numberOfStudentsOfClassRoomId(firstClassRoomId);
        long second = 0;
        if (secondClassRoomId != null)
            second = classRoomService.numberOfStudentsOfClassRoomId(secondClassRoomId);
        else
            second = studentService.findAll().size();
        barChart.getXValues().add(firstClassRoomName);
        barChart.getXValues().add((secondClassRoomName != null) ? secondClassRoomName : "All");
        barChart.getYValues().add(BigDecimal.valueOf(firstCRStudent));
        barChart.getYValues().add(BigDecimal.valueOf(second));
        time = LocalDateTime.now();
        cache.put(key, Pair.of(barChart, time));
    }
}
