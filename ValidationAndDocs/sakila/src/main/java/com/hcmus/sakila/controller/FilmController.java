package com.hcmus.sakila.controller;

import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService filmService;

    // KIET
    /**
     * Đếm tổng số phim trong hệ thống
     * Method: GET
     * Endpoint: /films/count
     * Description: Trả về tổng số lượng phim hiện có.
     *
     *
     * Lấy danh sách phim (Pagination)
     * Method: GET
     * Endpoint: /film
     * Query Params: page, size
     * Description: Trả về danh sách phim, hỗ trợ phân trang.
     *
     *
     * Tìm kiếm phim theo tiêu đề
     * Method: GET
     * Endpoint: /film/search
     * Query Params: title
     * Description: Trả về danh sách phim có tiêu đề khớp với từ khóa tìm kiếm.
     *
     *
     * Lấy chi tiết một bộ phim
     * Method: GET
     * Endpoint: /film/{film_id}
     * Description: Trả về thông tin chi tiết của một bộ phim theo film_id
     */

    // YEN
    /**
     * Cập nhật thông tin phim
     * Method: PUT
     * Endpoint: /film/{film_id}
     * Description: Cập nhật thông tin của một bộ phim.
     *
     *
     * Xóa phim
     * Method: DELETE
     * Endpoint: /film/{film_id}
     * Description: Xóa bộ phim theo film_id
     *
     *
     * Thêm phim mới
     * Method: POST
     * Endpoint: /film
     * Request Body:
     * json
     * Sao chép
     * Chỉnh sửa
     * {
     *   "title": "New Movie",
     *   "description": "Movie description",
     *   "release_year": 2023,
     *   "language_id": 1,
     *   "original_language_id": 2,
     *   "rental_duration": 5,
     *   "rental_rate": 4.99,
     *   "length": 120,
     *   "replacement_cost": 19.99,
     *   "rating": "PG",
     *   "special_features": ["Trailers", "Deleted Scenes"]
     * }
     * Description: Thêm một bộ phim mới vào database.
     */


    // AN
    @Operation(tags = "Film Service", summary = "Retrieve films by rating",
            description = "Retrieve films by a given rating.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/rating/{rating}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByRating(@PathVariable String rating,
                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                       @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            RatingType ratingEnum = RatingType.valueOf(rating.toUpperCase().replace("-", "_"));
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByRating(ratingEnum, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid rating: " + rating));
        }
    }

    @Operation(tags = "Film Service", summary = "Retrieve films by releasing year",
            description = "Retrieve films by a given releasing year.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/year/{releasing_year}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByReleasingYear(@PathVariable Integer releasing_year,
                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                       @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByReleaseYear(releasing_year, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid year: " + releasing_year));
        }
    }

    @Operation(tags = "Film Service", summary = "Retrieve films by language",
            description = "Retrieve films by a given language.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/language/{language_id}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByLanguage(@PathVariable Integer language_id,
                                                                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                              @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByLanguage(language_id, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid language id: " + language_id));
        }
    }

    @Operation(tags = "Film Service", summary = "Retrieve film statistics by rating",
            description = "Retrieve the number of films by each rating.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/statistics")
    public ResponseEntity<ResponseDto<Map<String, Long>>> getFilmStatisticsByRating() {
        try {
            ResponseDto<Map<String, Long>> response = filmService.getFilmStatisticsByRating();
            if (response.getData() == null || response.getData().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto<>(null, "An error occurred while retrieving film statistics."));
        }
    }

    // VINH
    /**
     * Thống kê số lượng phim theo rating
     * Method: GET
     * Endpoint: /film/statistics
     * Description: Trả về số lượng phim theo từng rating
     * {
     *   "G": 10,
     *   "PG": 25,
     *   "PG-13": 30,
     *   "R": 15,
     *   "NC-17": 5
     * }
     *
     *
     * Lấy danh sách phim có thời lượng dài nhất
     * Method: GET
     * Endpoint: /film/longest?size=10
     * Query Params: limit (số phim muốn lấy)
     * Description: Trả về danh sách các phim có thời lượng dài nhất.
     *
     *
     * Lấy danh sách phim có giá thuê cao nhất
     * Method: GET
     * Endpoint: /films/most_expensive
     * Query Params: limit
     * Description: Lấy danh sách các phim có giá thuê (rental_rate) cao nhất.
     */

    // API du tru, thay the: Chi ap dung cho truong hop qua kho, truong hop co ban khong duoc dung
    /**
     *  Lấy danh sách phim cập nhật gần đây nhất
     * Method: GET
     * Endpoint: /film/recently_updated
     * Query Params: limit
     * Description: Trả về danh sách các phim có last_update gần đây nhất.
     *
     *
     *  Lấy danh sách phim theo khoảng giá thuê
     * Method: GET
     * Endpoint: /films/rental_rate_range
     * Query Params: min, max
     * Description: Trả về danh sách phim có giá thuê (rental_rate) nằm trong khoảng được chỉ định.
     *
     *
     * Kiểm tra phim có tồn tại không
     * Method: HEAD
     * Endpoint: /films/{film_id}
     * Description: Kiểm tra xem film_id có tồn tại hay không mà không cần trả về nội dung.
     */
}