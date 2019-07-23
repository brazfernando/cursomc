package com.fernando.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fernando.cursomc.domain.Cliente;
import com.fernando.cursomc.domain.enums.TipoCliente;
import com.fernando.cursomc.dto.ClienteNewDTO;
import com.fernando.cursomc.resources.exception.FieldMessage;
import com.fernando.cursomc.services.ClienteService;
import com.fernando.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteService clienteService;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	private void validaCPF(List<FieldMessage> list, ClienteNewDTO objDto) {
		if(objDto.getTipoCliente() == TipoCliente.PESSOAFISICA.getCod() && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
	}
	
	private void validaCNPJ(List<FieldMessage> list, ClienteNewDTO objDto) {
		if(objDto.getTipoCliente() == TipoCliente.PESSOAJURIDICA.getCod() && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
	}
	
	private void validaUnicidadeEmail(List<FieldMessage> list, ClienteNewDTO objDto) {
		Cliente obj = clienteService.findByEmail(objDto.getEmail());
		if(obj != null && obj.getId() != null) {
			list.add(new FieldMessage("email", "O E-mail informado já está cadastrados"));
		}
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		validaCPF(list, objDto);
		
		validaCNPJ(list, objDto);
		
		validaUnicidadeEmail(list, objDto);

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}