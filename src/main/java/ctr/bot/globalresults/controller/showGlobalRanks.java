package ctr.bot.globalresults.controller;


import ctr.bot.globalresults.entity.RankSchemas;
import ctr.bot.globalresults.repository.RankRespository;
import ctr.bot.globalresults.service.StartPublicService;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class showGlobalRanks {

	@Autowired
	private StartPublicService startPublicService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private RankRespository rankRespository;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RankSchemas> getAll(Model model) {
		return rankRespository.findAll();
	}

	@GetMapping(value = "/start_public")
	@ResponseBody
	public String startPublic() {
		String server = "";
		if(!StartPublicService.RUNNING) {
			server = startPublicService.startPublic();
			return "STARTED AT: " + server;
		}
		else {
			return "ALREADY RUNNING";
		}
	}

	@GetMapping("/ffa")
	public String getFFA(Model model) throws JsonProcessingException {
		model.addAttribute("modality", "ffa");
		model.addAttribute("players", objectMapper.writeValueAsString(rankRespository.findFFA()));
		return "globalResults";
	}

	@GetMapping("/duos")
	public String getDuos(Model model) throws JsonProcessingException {
		model.addAttribute("modality", "duos");
		model.addAttribute("players", objectMapper.writeValueAsString(rankRespository.findDuos()));
		return "globalResults";
	}

	@GetMapping("/war3vs3")
	public String get3vs3(Model model) throws JsonProcessingException {
		model.addAttribute("modality", "3vs3");
		model.addAttribute("players", objectMapper.writeValueAsString(rankRespository.find3vs3()));
		return "globalResults";
	}

	@GetMapping("/war4vs4")
	public String get4vs4(Model model) throws JsonProcessingException {
		model.addAttribute("modality", "4vs4");
		model.addAttribute("players", objectMapper.writeValueAsString(rankRespository.find4vs4()));
		return "globalResults";
	}

	@GetMapping("/itemless")
	public String getItemless(Model model) throws JsonProcessingException {
		model.addAttribute("modality", "itemless");
		model.addAttribute("players", objectMapper.writeValueAsString(rankRespository.findItemless()));
		return "globalResults";
	}

}