package edu.cuongnghiem.springschoolmanager.analyzer;

import edu.cuongnghiem.springschoolmanager.analyzer.charts.BarChart;

/**
 * Created by cuongnghiem on 24/10/2021
 **/

public interface ClassRoomAnalyzer {
    BarChart students(Long firstClassRoomId, Long secondClassRoomId, StringBuilder status);
}
