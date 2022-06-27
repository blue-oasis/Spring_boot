package com.mysite.sbb;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil { //마크다운 컴포넌트를 bean으로 등록, 템플릿에서 바로 사용 가능
    public String markdown(String markdown) { //마크다운 텍스트를 변환된 HTML로 리턴
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
