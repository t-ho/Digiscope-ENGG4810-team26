/*
 * frontend.h
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#ifndef FRONTEND_H_
#define FRONTEND_H_

typedef enum Channel
{
	CHANNEL_A = 0,
	CHANNEL_B = 1
} Channel;

typedef enum FrontendCoupling
{
	COUPLING_DC = 0,
	COUPLING_AC = 1
} FrontendCoupling;

extern void FrontEnd_Init(void);
extern uint32_t FrontEndGetHorDiv(void);
extern void FrontEndSetHorDiv(uint32_t us);
extern uint32_t FrontEndGetVerDiv(Channel channel);
extern void FrontEndSetVerDiv(Channel channel, uint32_t uV);
extern FrontendCoupling FrontEndGetCoupling(Channel channel);
extern void FrontEndSetCoupling(Channel channel, FrontendCoupling coupling);
extern void FrontEndNotify(void);

#endif /* FRONTEND_H_ */
