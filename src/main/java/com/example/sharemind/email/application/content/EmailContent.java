package com.example.sharemind.email.application.content;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.message.dto.response.MessageResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailContent {
    @Value("${server.url}")
    private String serverUrl;

    @Value("${server.chattingLink}")
    private String chattingUrl;

    private String getLink(UUID uuid) {
        return serverUrl + "/" + uuid.toString();
    }

    public String[] getLinkContent(Consult consult) {
        String subject = consult.getCounselor().getNickname()
                + "님께 상담 신청이 완료되었습니다. 아래 링크를 통해 상담 내용을 보내주세요.";
        String link = getLink(consult.getConsultUuid());
        String body = consult.getCounselor().getNickname()
                + "님께 상담 신청이 완료되었습니다. 아래 링크를 통해 상담 내용을 보내주세요."
                + "\n\n상담 링크 : \n"
                + link
                + "\n접속 비밀번호: \n"
                + consult.getCustomerPassword()
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getFirstApplyContent(Consult consult) {
        String subject = consult.getCustomer().getNickname()
                + "님이 상담을 신청하셨습니다. 아래 링크를 통해 답변해 주세요.";
        String link = getLink(consult.getConsultUuid());
        String body = consult.getCustomer().getNickname()
                + "님이 상담을 신청하셨습니다. 아래 링크를 통해 답변해 주세요."
                + "\n\n상담 링크 : \n"
                + link
                + "\n\n접속 비밀번호: \n"
                + consult.getCounselorPassword()
                + "\n에서 확인하실 수 있습니다."
                + "\n\n*24시간 내 답변이 이루어지지 않을 경우 상담이 취소되며, 미답변 취소가 누적되면 패널티를 받을 수 있습니다."
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getSecondApplyContent(Consult consult) {
        String subject = consult.getCustomer().getNickname()
                + "님이 추가 질문을 요청하였습니다.";
        String link = getLink(consult.getConsultUuid());
        String body = consult.getCustomer().getNickname()
                + "님이 추가 질문을 요청하였습니다. 추가 질문 답변이 완료되면 판매가 자동으로 확정됩니다."
                + "\n\n상담 링크: \n"
                + link
                + "\n\n접속 비밀번호: \n"
                + consult.getCounselorPassword()
                + "\n\n추가 질문에 대해 24시간 내 답변이 이루어지지 않을 경우 전체 거래가 취소되며 상담료가 환불되니 유의해 주세요."
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getFirstReplyContent(Consult consult) {
        String subject = consult.getCounselor().getNickname()
                + "님이 답변을 입력하셨습니다.";
        String link = getLink(consult.getConsultUuid());
        String body = "추가 질문이 있으실 경우 24시간 내에 추가 질문을 남겨주세요."
                + "24시간 경과 이후에는 추가 질문에 대해 답변을 받지 못하여도 결제 금액이 환불되지 않습니다."
                + "\n\n상담 링크 : \n"
                + link
                + "\n\n접속 비밀번호: \n"
                + consult.getCustomerPassword()
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getSecondReplyContent(Consult consult, String allMessageResponses) {//todo 리뷰 링크 추가
        String subject = consult.getCustomer().getNickname()
                + "님이 추가 질문에 대한 답변을 입력하였습니다.";
        String body = consult.getCustomer().getNickname()
                + "님이 추가 질문에 대한 답변을 입력하였습니다."
                + "\n\n상담이 모두 마무리되었으며 추가 상담을 원하실 경우 새롭게 상담 신청을 진행해 주세요."
                + "\n아래 링크를 통해 상담을 평가하실 수 있습니다.\n"
                + "\n셰어마인드를 이용해 주셔서 감사합니다."
                + "\n상담이 모두 마감되어 기존 상담 링크를 더 이상 이용하실 수 없습니다."
                + "\n상담 내용은 아래 첨부해 드립니다.\n\n"
                + allMessageResponses
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getNoAdditionalQuestionCustomerContent(Consult consult,
                                                           String allMessageResponses) {//todo 리뷰 링크 추가
        String subject = consult.getCounselor().getNickname()
                + "님과의 상담이 추가 질문 입력 기한이 경과되어 추가 질문 없이 종료되었습니다.";
        String body = "상담이 모두 마무리되었으며 추가 상담을 원하실 경우 새롭게 상담 신청을 진행해 주세요."
                + "\n 아래 링크를 통해 상담을 평가하실 수 있습니다.\n"
                + "\n셰어마인드를 이용해 주셔서 감사합니다."
                + "\n상담이 모두 마감되어 기존 상담 링크를 더 이상 이용하실 수 없습니다."
                + " 상담 내용은 아래 첨부해 드립니다.\n\n"
                + allMessageResponses
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getNoAdditionalQuestionCounselorContent(Consult consult) {
        String subject = consult.getCustomer().getNickname()
                + "님과의 상담이 추가 질문 입력 기한이 경과되어 추가 질문 없이 종료되었습니다.";
        String body = consult.getCustomer().getNickname()
                + "님과의 상담이 추가 질문 입력 기한이 경과되어 추가 질문 없이 종료되었습니다."
                + "\n상담이 확정되었으며 정산일에 대금이 정산될 예정입니다."
                + "\n셰어마인드를 이용해 주셔서 감사합니다."
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getClosureCounselorContent(Consult consult) {
        String subject = consult.getCustomer().getNickname()
                + "과의 상담이 모두 마무리되었습니다.";
        String body = consult.getCustomer().getNickname()
                + "과의 상담이 모두 마무리되었습니다."
                + "\n상담이 확정되었으며 정산일에 대금이 정산될 예정입니다."
                + "\n셰어마인드를 이용해 주셔서 감사합니다."
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getCancelCustomerContent(Consult consult) {
        String subject = consult.getCounselor().getNickname()
                + "님께 신청하신 상담에 대해 24시간 내 답변이 이루어지지 않아 상담이 취소되었습니다.";
        String body = consult.getCounselor().getNickname()
                + "님께 신청하신 상담에 대해 24시간 내 답변이 이루어지지 않아 상담이 취소되었습니다."
                + "\n\n아래 오픈 채팅을 통해 환불 또는 신청 변경 문의를 진행해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }

    public String[] getCancelCounselorContent(Consult consult) {
        String subject = consult.getCustomer().getNickname()
                + "님이 신청하신 상담에 대해 24시간 내 답변이 이루어지지 않아 상담이 취소되었습니다.";
        String body = consult.getCounselor().getNickname()
                + "님이 신청하신 상담에 대해 24시간 내 답변이 이루어지지 않아 상담이 취소되었습니다."
                + "\n*24시간 내 답변이 이루어지지 않을 경우 상담이 취소되며, 미답변 취소가 누적되면 패널티를 받을 수 있습니다."
                + "\n\n문의사항은 아래 오픈채팅 링크를 이용해 주시기 바랍니다.\n"
                + chattingUrl;
        return new String[]{subject, body};
    }
}
