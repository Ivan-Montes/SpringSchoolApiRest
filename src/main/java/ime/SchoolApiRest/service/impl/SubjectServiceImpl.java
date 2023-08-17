package ime.SchoolApiRest.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.entity.Subject;
import ime.SchoolApiRest.entity.Teacher;
import ime.SchoolApiRest.mapper.SubjectMapper;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.repository.TeacherRepository;
import ime.SchoolApiRest.service.SubjectService;

import ime.SchoolApiRest.exception.*;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Override
	public Set<SubjectDto> getAllEagerSubjectDto() {
		
		return SubjectMapper.toListSubjectDto(subjectRepo.getAllEagerSubject());
	}

	@Override
	public SubjectBasicDto getSubjectBasicDtoById(Long subjectId) {
		
		return SubjectMapper.toSubjectBasicDto(subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId)));
		
	}

	@Override
	public void deleteSubjectById(Long subjectId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		if ( subject.getTeacher() != null ) throw new TeacherAssociatedException(subjectId);
		if ( subject.getStudents() != null && !subject.getStudents().isEmpty() ) throw new StudentAssociatedException(subjectId);
		subjectRepo.deleteById(subjectId);
		
	}

	@Override
	public SubjectBasicDto createSubject(SubjectBasicCreationDto sbcDto) {
		
		return SubjectMapper.toSubjectBasicDto(subjectRepo.save(SubjectMapper.dtoCreationToSubject(sbcDto)));
		
	}

	@Override
	public SubjectBasicDto updateSubject(Long subjectId, SubjectBasicDto sbDto) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		subject.setName(sbDto.getName());
		Subject subjectUpdated = subjectRepo.save(subject);
		
		return SubjectMapper.toSubjectBasicDto(subjectUpdated);
		
	}

	@Override
	public SubjectDto addTeacherToSubject(Long subjectId, Long teacherId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Teacher teacher = teacherRepo.findById(teacherId).orElseThrow( ()-> new ResourceNotFoundException(teacherId) );
		subject.setTeacher(teacher);
		Subject subjectUpdated = subjectRepo.save(subject);
		
		return SubjectMapper.toSubjectDto(subjectUpdated);
		
	}

}
