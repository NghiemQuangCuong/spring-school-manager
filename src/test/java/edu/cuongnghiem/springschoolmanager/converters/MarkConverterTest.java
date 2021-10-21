package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.MarkCommand;
import edu.cuongnghiem.springschoolmanager.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkConverterTest {

    private final Long MARK_ID = 213L;
    private final float MARK_SCORE = 6.8f;
    private final Long STUDENT_ID = 242L;
    private final Long CLASS_ROOM_ID = 123L;
    private final Long CLASS_TYPE_ID = 314L;
    private final Long TEACHERS_SET_SIZE = 21L;
    private final Long EXAM_ID = 123L;
    private final Long SCHOOL_YEAR_ID = 1231L;
    private final Long SUBJECT_ID = 8561L;
    private final ClassType CLASS_TYPE = ClassType.builder().id(CLASS_TYPE_ID).build();
    private final Set<Teacher> TEACHERS_SET = new HashSet<>();
    {
        for (Long i = 0L; i < TEACHERS_SET_SIZE; i++) {
            TEACHERS_SET.add(Teacher.builder().id(i + 1).build());
        }
    }
    private final ClassRoom CLASS_ROOM = ClassRoom.builder().id(CLASS_ROOM_ID).classType(CLASS_TYPE).teachers(TEACHERS_SET).build();
    private final Student STUDENT = Student.builder().id(STUDENT_ID).classRoom(CLASS_ROOM).build();
    private final SchoolYear SCHOOL_YEAR = SchoolYear.builder().id(SCHOOL_YEAR_ID).build();
    private final Subject SUBJECT = Subject.builder().id(SUBJECT_ID).build();
    private final Exam EXAM = Exam.builder().id(EXAM_ID).schoolYear(SCHOOL_YEAR).subject(SUBJECT).build();

    MarkConverter markConverter;

    Mark mark;

    @BeforeEach
    void setUp() {
        markConverter =
                new MarkConverter(
                    new StudentConverter(
                            new ClassRoomConverter(
                                    new ClassTypeConverter(),
                                    new TeacherConverter(
                                         new ContactConverter())),
                            new ContactConverter()
                ),
                    new ExamConverter(
                            new SchoolYearConverter(),
                            new SubjectConverter()
                    )
        );

        mark = Mark.builder()
                .id(MARK_ID)
                .score(MARK_SCORE)
                .student(STUDENT)
                .exam(EXAM)
                .build();
    }

    @Test
    void entityToCommand() {
        MarkCommand command = markConverter.entityToCommand(mark);
        assertEquals(MARK_ID, command.getId());
        assertEquals(MARK_SCORE, command.getScore());
        assertEquals(EXAM_ID, command.getExamCommand().getId());
        assertEquals(SCHOOL_YEAR_ID, command.getExamCommand().getSchoolYearCommand().getId());
        assertEquals(SUBJECT_ID, command.getExamCommand().getSubjectCommand().getId());
        assertEquals(STUDENT_ID, command.getStudentCommand().getId());
        assertEquals(CLASS_ROOM_ID, command.getStudentCommand().getClassRoomCommand().getId());
        assertEquals(CLASS_TYPE_ID, command.getStudentCommand().getClassRoomCommand().getClassTypeCommand().getId());
        assertEquals(TEACHERS_SET_SIZE, command.getStudentCommand().getClassRoomCommand().getTeacherCommands().size());
    }

    @Test
    void commandToEntity() {
        MarkCommand command = markConverter.entityToCommand(mark);
        Mark entity = markConverter.commandToEntity(command);
        assertEquals(MARK_ID, entity.getId());
        assertEquals(MARK_SCORE, entity.getScore());
        assertEquals(EXAM_ID, entity.getExam().getId());
        assertEquals(SCHOOL_YEAR_ID, entity.getExam().getSchoolYear().getId());
        assertEquals(SUBJECT_ID, entity.getExam().getSubject().getId());
        assertEquals(STUDENT_ID, entity.getStudent().getId());
        assertEquals(CLASS_ROOM_ID, entity.getStudent().getClassRoom().getId());
        assertEquals(CLASS_TYPE_ID, entity.getStudent().getClassRoom().getClassType().getId());
        assertEquals(TEACHERS_SET_SIZE, entity.getStudent().getClassRoom().getTeachers().size());
    }
}