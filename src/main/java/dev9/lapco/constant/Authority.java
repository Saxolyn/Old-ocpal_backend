package dev9.lapco.constant;

import java.util.ArrayList;
import java.util.List;

public class Authority {

    public final static String AUTH_02_LEVEL = "Authenticate by OTP";

    public final static String READ_SCHEDULE = "Read schedule of mapping class";

    public final static String WRITE_TEST = "Doing Test";

    public final static String READ_LESSION_LIST = "Display Lession List";

    public final static String CREATE_QUESTION_LIST = "Create Question List";

    public final static String CREATE_TEST = "Create Test from question list";

    public final static String ACTIVE_TEST = "Active test for specific student";

    public final static String READ_RECORD = "Read record after test";

    public final static String ASSIGN_TEST = "Assign test for specific class";

    public final static String READ_STUDENT_LIST = "Manage Student List";

    public final static String CREATE_STUDENT = "Create Student";

    public final static String UPDATE_STUDENT = "Update Student";

    public final static String DELETE_STUDENT = "Delete Student";

    public final static String CREATE_ZOOM_SCHEDULE = "Create Schedule by Zoom";

    public final static String UPDATE_PASSWORD = "Update Password";

    public final static String UPDATE_PASSWORD_AUTH_02_LEVEL = "Update Password by Auth 02 Level";

    public final static String READ_AGGREGATE_RESULT = "Read Aggregate Result";

    public final static String READ_CLASS_SCHEDULE = "Read Class Schedule";

    public final static String CREATE_CLASS_SCHEDULE = "Create Class Schedule";

    public final static String UPDATE_CLASS_SCHEDULE = "Update Class Schedule";

    public final static String DELETE_CLASS_SCHEDULE = "Delete Class Schedule";

    public final static String ASSIGN_CLASS_PROGRESS = "Assign Class Progress";

    public final static String READ_CLASS_LIST = "Read Class List";

    public final static String CREATE_CLASS = "Create Class";

    public final static String UPDATE_CLASS = "Update Class";

    public final static String DELETE_CLASS = "Delete Class";

    public final static String IMPORT_DATA = "Import Data";

    public final static String EXPORT_CLASS_LIST = "Export Class List";

    public final static String MANAGE_STUDENT_RECORD = "Manage Student Record between teacher";

    public final static String CREATE_PROGRESS = "Create Progress";

    public final static String UPDATE_PROGRESS = "Update Progress";

    public final static String DELETE_PROGRESS = "Delete Progress";

    public final static String READ_PROGRESS = "Read Progress";

    public final static String EXPORT_TEST_RESULT = "Export Test Result";

    public final static String CREATE_TEACHER = "Create Teacher account";

    public final static String READ_TEACHER_LIST = "Read Teacher List";

    public final static String UPDATE_TEACHER = "Update Teacher";

    public final static String DELETE_TEACHER = "Delete Teacher";

    public final static String EXPORT_TEACHER_LIST = "Export Teacher List";

    public final static String ASSIGN_TEACH_PROGRESS = "Assign Teacher Progress";

    public final static String ASSIGN_RETAKE_CLASS = "Assign Student to a retake class";

    public final static String READ_LOG = "Read sign-in Log";

    public final static String READ_TEACHER_LOG = "Read Teacher Log";

    public final static String EXPORT_HISTORY = "Export History";

    public final static String READ_ADMIN_LIST = "Read Admin List";

    public final static String READ_ADMIN = "Read admin action list";

    public final static String UPDATE_ADMIN = "update admin account";

    public final static String DELETE_ADMIN = "Delete admin account";

    public final static String CREATE_ADMIN = "create admin account";

    public static List<String> getSuperAdminAuthority() {
        return List.of(
                AUTH_02_LEVEL,
                UPDATE_PASSWORD,
                UPDATE_PASSWORD_AUTH_02_LEVEL,
                CREATE_ADMIN,
                READ_ADMIN,
                UPDATE_ADMIN,
                DELETE_ADMIN,
                CREATE_TEACHER,
                READ_TEACHER_LIST,
                UPDATE_TEACHER,
                DELETE_TEACHER,
                CREATE_STUDENT,
                READ_STUDENT_LIST,
                UPDATE_STUDENT,
                DELETE_STUDENT,
                ASSIGN_RETAKE_CLASS,
                CREATE_CLASS_SCHEDULE,
                READ_CLASS_SCHEDULE,
                UPDATE_CLASS_SCHEDULE,
                DELETE_CLASS_SCHEDULE,
                CREATE_ZOOM_SCHEDULE,
                CREATE_CLASS,
                READ_CLASS_LIST,
                UPDATE_CLASS,
                DELETE_CLASS,
                ASSIGN_CLASS_PROGRESS,
                MANAGE_STUDENT_RECORD,
                CREATE_PROGRESS,
                READ_PROGRESS,
                UPDATE_PROGRESS,
                DELETE_PROGRESS,
                READ_LESSION_LIST,
                CREATE_QUESTION_LIST,
                CREATE_TEST,
                ACTIVE_TEST,
                READ_RECORD,
                EXPORT_TEST_RESULT,
                ASSIGN_TEST,
                IMPORT_DATA,
                EXPORT_HISTORY,
                EXPORT_CLASS_LIST,
                EXPORT_TEACHER_LIST,
                EXPORT_TEST_RESULT,
                READ_AGGREGATE_RESULT,
                READ_LOG,
                READ_TEACHER_LOG

        );
    }

    public List<String> getAdminAuthority() {
        return List.of(
                AUTH_02_LEVEL,
                UPDATE_PASSWORD,
                UPDATE_PASSWORD_AUTH_02_LEVEL,
                READ_AGGREGATE_RESULT,
                CREATE_CLASS_SCHEDULE,
                READ_CLASS_SCHEDULE,
                UPDATE_CLASS_SCHEDULE,
                DELETE_CLASS_SCHEDULE,
                ASSIGN_CLASS_PROGRESS,
                CREATE_CLASS,
                READ_CLASS_LIST,
                UPDATE_CLASS,
                DELETE_CLASS,
                ASSIGN_CLASS_PROGRESS,
                IMPORT_DATA,
                EXPORT_CLASS_LIST,
                MANAGE_STUDENT_RECORD,
                CREATE_PROGRESS,
                READ_PROGRESS,
                UPDATE_PROGRESS,
                DELETE_PROGRESS,
                READ_LESSION_LIST,
                CREATE_QUESTION_LIST,
                CREATE_TEST,
                ACTIVE_TEST,
                READ_RECORD,
                ASSIGN_TEST,
                CREATE_TEACHER,
                READ_TEACHER_LIST,
                UPDATE_TEACHER,
                DELETE_TEACHER,
                CREATE_STUDENT,
                READ_STUDENT_LIST,
                UPDATE_STUDENT,
                DELETE_STUDENT,
                ASSIGN_RETAKE_CLASS,
                CREATE_ZOOM_SCHEDULE,
                EXPORT_HISTORY,
                EXPORT_TEACHER_LIST,
                EXPORT_TEST_RESULT,
                READ_LOG,
                READ_TEACHER_LOG
                );
    }

    public List<String> getTeacherAuthority() {
        return List.of(
                READ_SCHEDULE,
                READ_LESSION_LIST,
                CREATE_QUESTION_LIST,
                CREATE_TEST,
                ACTIVE_TEST,
                READ_RECORD,
                ASSIGN_TEST,
                READ_STUDENT_LIST,
                CREATE_ZOOM_SCHEDULE
        );
    }

    public List<String> getStudentAuthority() {
        return List.of(WRITE_TEST);
    }





















}
