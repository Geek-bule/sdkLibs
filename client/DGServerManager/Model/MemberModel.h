//
//  MemberModel.h
//  Tan_SwipeTableViewCell
//
//  Created by PX_Mac on 16/3/26.
//  Copyright © 2016年 PX_Mac. All rights reserved.
//  会员信息

#import <Foundation/Foundation.h>

//屏幕宽度
#define SCREENWIDTH [UIScreen mainScreen].bounds.size.width
#define SCREENHEIGHT [UIScreen mainScreen].bounds.size.height

@interface MemberModel : NSObject

@property (nonatomic, copy) NSString *gameId; //会员ID
@property (nonatomic, copy) NSString *gameName; //会员显示的名字
@property (nonatomic, copy) NSString *gameCode; //会员邮箱
@property (nonatomic, copy) NSString *mobileType; //会员邮箱

+ (instancetype)memberWithID: (NSString*)gameID gameName: (NSString *)gameName gameCode:(NSString*)gameCode mobileType: (NSString *)mobileType;

@end
// 版权属于原作者
// http://code4app.com (cn) http://code4app.net (en)
// 发布代码于最专业的源码分享网站: Code4App.com
