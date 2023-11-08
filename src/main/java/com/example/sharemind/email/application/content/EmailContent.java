package com.example.sharemind.email.application.content;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

public class EmailContent {
    @Value("${server.url}")
    private static String serverUrl;

    private static String getLink(UUID uuid) {
        return serverUrl + "/" + uuid.toString();
    }

    public static String[] getConsultationLinkContent(Long password, UUID consultUuid) {
        String subject = "[sharemind]상담 링크입니다.";
        String link = getLink(consultUuid);
        String body = "상담 링크 : \n" + link + "\n\n링크 비밀번호: " + password;
        return new String[]{subject, body};
    }

    public static String[] getConsultationApplyCustomerContent(Long password, UUID consultUuid) {
        String subject = "[sharemind]상담 신청이 완료되었습니다.";
        String link = getLink(consultUuid);
        String body = "상담 신청이 완료되었습니다. 상담 번호: \n자세한 내용은 " + link + "에서 확인하실 수 있습니다.";
        return new String[]{subject, body};
    }

    public static String[] getConsultationApplyCounselorContent(Long password, UUID consultUuid) {
        String subject = "[sharemind]상담 신청이 완료되었습니다.";
        String link = getLink(consultUuid);
        String body = "상담 신청이 완료되었습니다. 상담 번호: \n자세한 내용은 " + link + "에서 확인하실 수 있습니다.";
        return new String[]{subject, body};
    }

    public static String[] getConsultationReplyContent(Long password, UUID consultUuid) {
        String subject = "[sharemind]상담 답변이 완료되었습니다.";
        String link = getLink(consultUuid);
        String body = "상담 답변이 완료되었습니다.\n자세한 내용은 " + link + "에서 확인하실 수 있습니다.";
        return new String[]{subject, body};
    }

}
