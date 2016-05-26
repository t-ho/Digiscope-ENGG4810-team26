/*
 * frontend.h
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#ifndef FRONTEND_H_
#define FRONTEND_H_

extern uint32_t FrontEndGetHorDiv(void);
extern void FrontEndSetHorDiv(uint32_t us);
extern uint32_t FrontEndGetVerDiv(uint32_t channel);
extern void FrontEndSetVerDiv(uint32_t channel, uint32_t uV);
extern uint32_t FrontEndGetCoupling(uint32_t channel);
extern void FrontEndSetCoupling(uint32_t channel, uint32_t coupling);

#endif /* FRONTEND_H_ */
