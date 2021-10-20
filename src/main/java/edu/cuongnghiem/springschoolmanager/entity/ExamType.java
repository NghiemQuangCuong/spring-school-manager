package edu.cuongnghiem.springschoolmanager.entity;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

public enum ExamType {
    KTM1("Kiểm tra miệng 1"),
    KTM2("Kiểm tra miệng 2"),
    KT15P1("Kiểm tra 15 phút 1"),
    KT15P2("Kiểm tra 15 phút 2"),
    KT15P3("Kiểm tra 15 phút 3"),
    KT1T1("Kiểm tra 1 tiết 1"),
    KT1T2("Kiểm tra 1 tiết 2"),
    KT1T3("Kiểm tra 1 tiết 3"),
    KTCK("Kiểm tra cuối kỳ");

    private String name;

    ExamType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
