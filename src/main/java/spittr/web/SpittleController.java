package spittr.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.entities.Spittle;
import spittr.repositories.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
//		Page<Spittle> page = spittleRepository.findAll(createPageRequest(max, count));
//		return page.getContent();

		return spittleRepository.findAll();
	}
	
//	private final Pageable createPageRequest(long max, int count) {
//	    return new PageRequest(1, count, new Sort(Sort.Direction.DESC, "time"));
//	}

	// This is another way to write spittles without directly referenceing the
	// model
	// @RequestMapping(method=RequestMethod.GET)
	// public List<Spittle> spittles() {
	// return spittleRepository.findSpittles(Long.MAX_VALUE, 20));
	// }

	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		model.addAttribute("spittle", spittleRepository.findOne(spittleId));
		return "spittle";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveSpittle(SpittleForm form, Model model) throws Exception {	
		spittleRepository
				.save(new Spittle(null, form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
		return "redirect:/spittles";
	}
}