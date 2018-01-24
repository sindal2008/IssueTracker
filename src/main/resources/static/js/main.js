var baseUrl = $('#baseUrl').attr('content');

function leaveComment(issueId) {
    $('#comment-link-' + issueId).removeClass('show');
    $('#comment-link-' + issueId).addClass('hidden');

    $('#comment-' + issueId).removeClass('hidden');
    $('#comment-' + issueId).addClass('show');
}

function sendComment(issueId) {
    var text = $('#comment-' + issueId + ' > textarea').val();
    $.ajax({
        url: baseUrl + 'comment/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            issueId: issueId,
            text: text
        })
    }).done(function () {
        location.reload();
    });
}

function cancelComment(issueId) {
    $('#comment-link-' + issueId).removeClass('hidden');
    $('#comment-link-' + issueId).addClass('show');

    $('#comment-' + issueId).removeClass('show');
    $('#comment-' + issueId).addClass('hidden');
}