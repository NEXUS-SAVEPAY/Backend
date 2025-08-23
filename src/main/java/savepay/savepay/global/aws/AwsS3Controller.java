package savepay.savepay.global.aws;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.global.ApiResponse;

@RestController
@RequestMapping("/api/aws")
@RequiredArgsConstructor
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping(name = "/", consumes = "multipart/form-data")
    public ApiResponse<String> postImage(@RequestPart(name = "ImageFile", required = false)
                                             MultipartFile multipartFile) {
        return ApiResponse.onSuccess(awsS3Service.uploadFile(multipartFile));
    }
}
