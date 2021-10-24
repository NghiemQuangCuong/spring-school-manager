package edu.cuongnghiem.springschoolmanager.analyzer.charts;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuongnghiem on 24/10/2021
 **/
@Data
@NoArgsConstructor
public class BarChart {
    private List<String> xValues = new ArrayList<>();
    private List<BigDecimal> yValues = new ArrayList<>();
}
