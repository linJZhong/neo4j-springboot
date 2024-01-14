package com.zhong.controller;

import com.zhong.node.Area;
import com.zhong.repository.AreaRepository;
import com.zhong.result.ResponseMessage;
import com.zhong.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/v1.0/database/area")
public class AreaController {

	@Autowired
	AreaRepository areaRepository;

	@RequestMapping("/get")
	public ResponseResult GetArea(@RequestParam(value = "name") String name) {
		Area area = areaRepository.findByName(name);

		if (area != null) {
			// 打印下name的关系，以下关系！=null，就是一个set集合，集合未做处理！
			System.out.println(area.getProvince());
			System.out.println("========省份【" + area.getName() + "】城市============");
			System.out.println(area.getCity());

			return new ResponseResult(ResponseMessage.OK);
		}
		return new ResponseResult(ResponseMessage.INTERNAL_SERVER_ERROR);
	}
}
