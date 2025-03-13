package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * <p>
     * <p>
     * Lấy danh sách phim (Pagination)
     * Method: GET
     * Endpoint: /film
     * Query Params: page, size
     * Description: Trả về danh sách phim, hỗ trợ phân trang.
     * <p>
     * <p>
     * Tìm kiếm phim theo tiêu đề
     * Method: GET
     * Endpoint: /film/search
     * Query Params: title
     * Description: Trả về danh sách phim có tiêu đề khớp với từ khóa tìm kiếm.
     * <p>
     * <p>
     * Lấy chi tiết một bộ phim
     * Method: GET
     * Endpoint: /film/{film_id}
     * Description: Trả về thông tin chi tiết của một bộ phim theo film_id
     */

    @PutMapping("update/{film_id}")
    ResponseDto<Object> updateFilm(@PathVariable Integer film_id, @RequestBody FilmUpdateDto film) {
        filmService.updateFilm(film_id, film);
        return ResponseDto.builder()
                .message("Film updated")
                .build();
    }

    @DeleteMapping("/delete/{film_id}")
    ResponseDto<Object> deleteFilm(@PathVariable Integer film_id) {
        filmService.deleteFilm(film_id);
        return ResponseDto.builder()
                .message("Film deleted ")
                .build();
    }

    @PostMapping("/create")
    ResponseDto<Object> createFilm(@RequestBody FilmCreateDto film) {
        filmService.createFilm(film);
        return ResponseDto.builder()
                .message("Film created")
                .build();
    }

    // AN
    /**
     * Lấy danh sách n phim theo xếp hạng
     * Method: GET
     * Endpoint: /film/rating/{rating}?size=10
     * Query Params: rating
     * Description: Lọc danh sách phim theo rating (G, PG, PG-13, R, NC-17).
     *
     *
     *Lấy danh sách phim theo năm phát hành
     * Method: GET
     * Endpoint: /film/year/{release_year}?size=10
     * Description: Trả về danh sách phim phát hành trong năm release_year.
     *
     *
     * Lấy danh sách phim có phụ đề theo một ngôn ngữ cụ thể
     * Method: GET
     * Endpoint: /film/language/{language_id}?size=10
     * Description: Trả về danh sách phim có ngôn ngữ hoặc phụ đề phù hợp với language_id.
     *
     */

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