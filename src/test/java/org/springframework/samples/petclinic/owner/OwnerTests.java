/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OwnerTests {

	@Test
	void addPetAddsPersistedPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(5);
		pet.setName("Buddy");

		owner.addPet(pet);

		assertTrue(owner.getPets().contains(pet));
		assertEquals(1, owner.getPets().size());
	}

	@Test
	void addPetDoesNotAddDuplicatePet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(5);
		pet.setName("Buddy");

		owner.addPet(pet);
		owner.addPet(pet);

		assertEquals(1, owner.getPets().size());
	}

	@Test
	@DisplayName("[QP-SMPET-9-1] Formats a 10-digit telephone into the parenthesised form")
	void formatsTenDigitTelephoneIntoParenthesisedForm() {
		Owner owner = new Owner();
		owner.setTelephone("6085551023");

		assertEquals("(608) 555-1023", owner.getFormattedTelephone());
	}

	@Test
	@DisplayName("[QP-SMPET-9-2] Returns a 9-digit telephone unchanged")
	void returnsNineDigitTelephoneUnchanged() {
		Owner owner = new Owner();
		owner.setTelephone("608555102");

		assertEquals("608555102", owner.getFormattedTelephone());
	}

	@Test
	@DisplayName("[QP-SMPET-9-3] Returns a 10-digit telephone containing dashes unchanged")
	void returnsTenDigitTelephoneWithDashesUnchanged() {
		Owner owner = new Owner();
		owner.setTelephone("608-555-1023");

		assertEquals("608-555-1023", owner.getFormattedTelephone());
	}

	@Test
	@DisplayName("[QP-SMPET-9-4] Returns an empty stored telephone as an empty string")
	void returnsEmptyStoredTelephoneAsEmptyString() {
		Owner owner = new Owner();
		owner.setTelephone("");

		assertEquals("", owner.getFormattedTelephone());
	}

	@Test
	@DisplayName("[QP-SMPET-9-5] Returns an empty string when the owner has no telephone")
	void returnsEmptyStringWhenTelephoneMissing() {
		Owner owner = new Owner();
		owner.setTelephone(null);

		assertEquals("", owner.getFormattedTelephone());
	}

	@Test
	@DisplayName("[QP-SMPET-9-6] Formats a 10-digit telephone with no web layer or database started")
	void formatsTelephoneWithoutSpringContext() {
		Owner owner = new Owner();
		owner.setTelephone("6085551023");

		assertEquals("(608) 555-1023", owner.getFormattedTelephone());
	}

}
