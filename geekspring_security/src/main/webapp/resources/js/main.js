$(document).ready(function () {
    $('.removeBtn').on('click', function (event) {
        var studentId = $(this).attr('entryIndex');
        $.get("/students/remove/" + studentId).done(function(){
            location.reload();
        });
    });

    $('#consoleTestBtn').on('click', function (event) {
        console.log($('#myInput').val());
    });

    $('#student_courses tr').click(function () {
        var course_id = $(this).find('td').eq(0).text();
        var student_id = window.location.pathname.split("/")[2];
        $.get("/students/deleteCourse/" + student_id + "/" + course_id).done(function(){
            location.reload();
        });
    });

    $('#all_courses tr').click(function () {
        var course_id = $(this).find('td').eq(0).text();
        var student_id = window.location.pathname.split("/")[2];
        $.get("/students/addCourse/" + student_id + "/" + course_id).done(function(){
            location.reload();
        });
    });
});