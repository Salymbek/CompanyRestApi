package peaksoft.dto.response.lesson;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse{
    private Long id;
    private String lessonName;
}
