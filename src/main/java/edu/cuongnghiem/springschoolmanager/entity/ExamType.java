package edu.cuongnghiem.springschoolmanager.entity;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

public enum ExamType {
    KTM1_HK1("HK1", "KTM1"),
    KTM2_HK1("HK1", "KTM2"),
    KT15P1_HK1("HK1", "KT15P1"),
    KT15P2_HK1("HK1", "KT15P2"),
    KT1T1_HK1("HK1", "KT1T1"),
    KT1T2_HK1("HK1", "KT1T2"),
    KTCK_HK1("HK1", "KTCK1"),
    KTM1_HK2("HK2", "KTM1"),
    KTM2_HK2("HK2", "KTM2"),
    KT15P1_HK2("HK2", "KT15P1"),
    KT15P2_HK2("HK2", "KT15P2"),
    KT1T1_HK2("HK2", "KT1T1"),
    KT1T2_HK2("HK2", "KT1T2"),
    KTCK_HK2("HK2", "KTCK2");

    private String semester;
    private String justName;

    ExamType(String semester, String justName) {
        this.semester = semester;
        this.justName = justName;
    }

    public String getSemester() {
        return semester;
    }
    public String getJustName() {
        return justName;
    }
}
